package br.ufpr.hellow;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HelloActivity extends Activity {
	
	private double num1, num2 ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        
        Button btMsg = (Button) findViewById(R.id.btMsg) ;
        
        btMsg.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText text1 = (EditText) findViewById(R.id.num1) ;
		        EditText text2 = (EditText) findViewById(R.id.num2) ;
		        num1 = Double.parseDouble(text1.getText().toString()) ;
		        num2 = Double.parseDouble(text2.getText().toString()) ;
				soma(num1, num2) ;
			}
		});
    }
    
    public void alerta2(){
    	Toast.makeText(this, R.string.msg, Toast.LENGTH_SHORT).show() ;
    }
    
    public void soma(double one, double two){
    	HashMap<String, Double> params = new HashMap<String, Double>();
    	params.put("numero1", one);
    	params.put("numero2", two);
    	JSONObject jsonParams = new JSONObject(params);
    	//Alterado - a string da url (ip do serviço) é modificada via strings.xml
    	JSONObject resp = HttpClient.SendHttpPost(this.getString(R.string.url_ws), jsonParams);
    	//JSONObject resp = HttpClient.SendHttpPost("http://10.0.2.2:8080/jersey/teste/somar", jsonParams);
    	//resp é o JSON gerado pelo webservice
    	//Exemplo:
    	Double soma = null;
    	try {
			soma = resp.getDouble("resultado");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Toast.makeText(this, "Resultado: "+soma, Toast.LENGTH_SHORT).show() ;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hello, menu);
        return true;
    }
}
