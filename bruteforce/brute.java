import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ricar on 22/11/2015.
 */
class Brute extends Thread {
        URL url;
        boolean success = false;
        String pass;

        Brute(URL url, String pass) {
            this.url = url;
            this.pass = pass;
        }

        public void run() {
            HttpURLConnection con = null;

            System.out.println("Olaaaaaaaaaaaaaa");

            try {
                con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "Mozilla/5.0  etc.");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                con.setRequestProperty("Connection", "keep-alive");
                con.setRequestProperty("Accept", "*/*");
                con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //add request header

            String urlParameters = "name=userName&pwd="+pass;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr;
            try {
                wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                int responseCode = con.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedReader in;
            try {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //if password is not correct, my form should return false
                if (response.toString().equals("false"))
                    success= false;
                else
                    success= true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    public String getPassword(){return pass;}
    public boolean isSuccess(){return success;}
}