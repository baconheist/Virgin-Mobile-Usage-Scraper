package net.bacondev.UsageMeter;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.http.util.ByteArrayBuffer;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UsageMeter extends Activity {
    /** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		final TextView tv = new TextView(this);
		
	    setContentView(R.layout.main);
	    
	    Button button = (Button) findViewById(R.id.android_button);
	    
        button.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
        	EditText edittext = (EditText) findViewById(R.id.edittext); 
        	Toast.makeText(UsageMeter.this, edittext.getText(),Toast.LENGTH_SHORT).show();
      
        	System.out.println(edittext.getText());
	
        	
        }


        });
        
        final EditText edittext = (EditText) findViewById(R.id.edittext);
        edittext.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                  Toast.makeText(UsageMeter.this, edittext.getText(), Toast.LENGTH_SHORT).show();
                  return true;
                }
                return false;
            }


        });
        

        
		 String myString = null;
		 
    	
    	
        try {
            URL myURL = new URL("http://www.baconheist.com/android/scraper.php");
     // Open a connection to that URL. 
            URLConnection ucon = myURL.openConnection();

     //start my stuff...
     ucon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
     ucon.setRequestProperty("METHOD", "POST");
     ucon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
     
     ucon.setDoOutput(true);
     OutputStream os = ucon.getOutputStream();
     String data = URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("623364", "UTF-8");
     data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("0405739890", "UTF-8");

     byte[] bytearray = data.getBytes();
     //System.out.print(data);
     os.write(bytearray);
     os.close();
     
     //finish my stuff...
     
     
     // Define InputStreams to read from the URLConnection.
            InputStream is = ucon.getInputStream();
     BufferedInputStream bis = new BufferedInputStream(is);
     
  
     
     
     // Read bytes to the Buffer until there is nothing more to read(-1).
             ByteArrayBuffer baf = new ByteArrayBuffer(50);
     int current = 0;
     while((current = bis.read()) != -1){
          baf.append((byte)current);
     }

     // Convert the Bytes read to a String.
           myString = new String(baf.toByteArray());
} catch (Exception e) {
     // On any Error we want to display it.
             myString = e.getMessage();
}
//Show the String on the GUI



  // tv.setText(myString);
       // this.setContentView(tv);        


}
	
}
