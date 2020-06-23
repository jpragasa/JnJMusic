
package com.ex.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.InstrumentSQLRepository;
import data.TransRepo;
import models.InstrumentModel;
import models.Transactions;
import org.apache.log4j.Logger;
import utils.PostgresConnectionUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class InstrumentServlet extends HttpServlet {
    /**
     *
     * The InstrumentServlet class responds to the Ebay API and converts the data into Json and
     * acts accordingly to the different requests.
     *
     */
    final static Logger logger = Logger.getLogger(InstrumentServlet.class);
    // Handles the Post request from the API
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        if (data.get("buy") == null) {


            try {
                String json = null;
                URL src = new URL(data.get("src").getAsString());
                Float price = data.get("price").getAsFloat();
                String upc = data.get("upc").getAsString();
                Integer catid = new Integer(data.get("catid").getAsString());
                String sale = data.get("sale").getAsString();
                String catdes = data.get("catdes").getAsString();
                InstrumentSQLRepository repo = new InstrumentSQLRepository(new PostgresConnectionUtil());
                repo.save(new InstrumentModel(upc, sale, catid, catdes, price, true, src));
                InstrumentModel temp = repo.findById(upc);
                Map<String, String> options = new LinkedHashMap<>();
                options.put("src", (String.valueOf(temp.getImage_url())));
                options.put("price", (String.valueOf(temp.getPrice())));
                options.put("upc", (String.valueOf(temp.getUPC())));
                options.put("des", (String.valueOf(temp.getCatName())));
                options.put("cat", (String.valueOf(temp.getCat())));
                options.put("sale", (String.valueOf(temp.getSale())));
                options.put("available", String.valueOf(temp.getAvailable()));
                json = new Gson().toJson(options);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);

            } catch (Exception e) {
                logger.error("Something dun goofed ", e);
            }
        } else if (data.get("buy").getAsString().equals("yes")) {

        }

    }

    //Called for nothing/ no purposes
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String json = null;
        TransRepo repo = new TransRepo(new PostgresConnectionUtil());
        List<Transactions> all = repo.findAll();
        Map<String, List> options = new LinkedHashMap<>();
        options.put("trans", all);
        //System.out.println(options);
        json = new Gson().toJson(options);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

    //
    //Called to Update  instrument availability
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        if (data.get("buy") == null)
        {
            try
            {
                String json = null;
                Float price = data.get("price").getAsFloat();
                String upc = data.get("upc").getAsString();
                String email = data.get("email").getAsString();
                TransRepo repo = new TransRepo(new PostgresConnectionUtil());
                repo.save(new Transactions(upc,email,price));
                Map<String, String> options = new LinkedHashMap<>();
                options.put("transID", (String.valueOf(5)));
                json = new Gson().toJson(options);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
                //System.out.println(json);

            }
            catch (Exception e)
            {
                logger.error("Something dun goofed ", e);
            }
        }
    }

}

