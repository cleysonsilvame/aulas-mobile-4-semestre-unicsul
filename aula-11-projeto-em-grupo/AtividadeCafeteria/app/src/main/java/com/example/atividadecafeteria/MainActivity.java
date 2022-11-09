package com.example.atividadecafeteria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private int qualSolicitacao = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirSobre(View v) {
        Intent intent = new Intent(this, Sobre.class);
        startActivity(intent);
    }

    public void listarCardapio(View view) {
        qualSolicitacao = 1;
        if (checkInternetConection()) {
            progressDialog = ProgressDialog.show(this, "", "Baixando dados");
            new DownloadJson().execute("http://mfpledon.com.br/cafeteria.php");
        } else {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Sem conexão. Verifique.", Toast.LENGTH_LONG).show();
        }
    }

    //android:onClick="lerPaises"
    public void listTipo(View view) {  //este método atende o evento de click no botão Países
        qualSolicitacao = 2;
        if (checkInternetConection()) {
            progressDialog = ProgressDialog.show(this, "", "Obtendo dados");
            new DownloadJson().execute("http://mfpledon.com.br/cafeteria.php");
        } else {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Sem conexão. Verifique.", Toast.LENGTH_LONG).show();
        }
    }


    public boolean checkInternetConection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public void mostrarCardapio(String strjson) {
        String data = "";
        try {
            JSONObject objRaiz = new JSONObject(strjson);
            JSONArray jsonArray = objRaiz.optJSONArray("itenscafeteria");
            JSONObject jsonObject = null;

            double total = 0;

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                Integer codproduto = jsonObject.optInt("codproduto");
                String nomeproduto = jsonObject.optString("nomeproduto");
                String tipoproduto = jsonObject.optString("tipoproduto");

                double precoproduto = jsonObject.optDouble("precoproduto");

                total += precoproduto;
                data += MessageFormat.format("{0} / {1} / {2} / {3}", codproduto, nomeproduto, tipoproduto, precoproduto);

                data += "\n";


                jsonObject = null;
            }

            double media =  total / jsonArray.length();

            data += "\n\n";
            data += MessageFormat.format("A média de preço é: {0, number, #.##}", media);

            ((TextView) findViewById(R.id.dados)).setText(data);
            progressDialog.dismiss();
        } catch (JSONException e) {
        }
    }

    public void mostrarTipo(String strjson) {
        ((TextView) findViewById(R.id.dados)).setText(strjson);
        String input = ((TextView) findViewById(R.id.txtFiltroTipo)).getText().toString();
        String data = "";
        try {
            JSONObject objRaiz = new JSONObject(strjson);
            JSONArray jsonArray = objRaiz.optJSONArray("itenscafeteria");
            JSONObject jsonObject = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);


                String tipoproduto = jsonObject.optString("tipoproduto");

                if (tipoproduto.contains(input)) {
                    Integer codproduto = jsonObject.optInt("codproduto");
                    String nomeproduto = jsonObject.optString("nomeproduto");

                    double precoproduto = jsonObject.optDouble("precoproduto");

                    data += MessageFormat.format("{0} / {1} / {2} / {3}", codproduto, nomeproduto, tipoproduto, precoproduto);

                    data += "\n";
                }
            }
            ((TextView) findViewById(R.id.dados)).setText(data);
        } catch (JSONException e) {
            ((TextView) findViewById(R.id.dados)).setText(e.getMessage() + "\n\n" + data + "\n\n");
        } finally {
            progressDialog.dismiss();
        }
    }

    private class DownloadJson extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // params é um vetor onde params[0] é a URL
            try {
                return downloadJSON(params[0]);
            } catch (IOException e) {

                return "Erro";
            }
        }

        // onPostExecute exibe o resultado do AsyncTask
        @Override
        protected void onPostExecute(String result) {
            if(result.equals("Erro")) {
                progressDialog.dismiss();
                ((TextView) findViewById(R.id.dados)).setText("\nAlgum erro aconteceu. Revisar a URL solicitada, verifique a conexão com a Internet etc.");
                return;
            }
            if (qualSolicitacao == 1) {
                mostrarCardapio(result);
            }
            else {
                mostrarTipo(result);
            }
        }

        private String downloadJSON(String myurl) throws IOException {
            InputStream is = null;
            String respostaHttp = "Erro";
            HttpURLConnection conn = null;
            InputStream in = null;
            ByteArrayOutputStream bos = null;
            try {
                URL u = new URL(myurl);
                conn = (HttpURLConnection) u.openConnection();
                conn.setConnectTimeout(4000); // 4 segundos de timeout
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                in = conn.getInputStream();
                bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
                respostaHttp = bos.toString("UTF-8");
            } catch (Exception ex) {
                respostaHttp = "Erro";
            } finally {
                if (in != null) in.close();
            }
            return respostaHttp;
        }
    }
}