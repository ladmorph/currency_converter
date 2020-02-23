package com.example.currencyConverter.utils;

import com.example.currencyConverter.entities.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class CurrencyXmlHandler {

    ArrayList<Currency> currencies = new ArrayList<>();

    public CurrencyXmlHandler parse() {
        try {
            // Xml parser.
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();

            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp"); // Website with information about the currency.
            InputStream stream = url.openStream();
            Document doc = docBuilder.parse(stream);
            doc.getDocumentElement().normalize(); // convert to normal view

            NodeList nodeList = doc.getElementsByTagName("Valute");

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

            for (int x = 0, size = nodeList.getLength(); x < size; x++) {

                // Get and give the date from the form dd.MM.yyyy to yyyy.MM.dd.
                // This is necessary for the search to work correctly.
                String date = sdf2.format(sdf.parse(
                        doc.getElementsByTagName("ValCurs").item(0).getAttributes().getNamedItem("Date").getNodeValue()));

                //
                Currency currency = new Currency()

                        .setDate(date)
                        .setValuteId(nodeList.item(x).getAttributes().getNamedItem("ID").getNodeValue())
                        .setNumCode(Integer.valueOf(doc.getElementsByTagName("NumCode").item(x).getTextContent()))
                        .setCharCode(doc.getElementsByTagName("CharCode").item(x).getTextContent())
                        .setNominal(Integer.valueOf(doc.getElementsByTagName("Nominal").item(x).getTextContent()))
                        .setName(doc.getElementsByTagName("Name").item(x).getTextContent())
                        .setValue(doc.getElementsByTagName("Value").item(x).getTextContent());


                currencies.add(currency);
            }

            // We add it separately because this currency is not available, but it is needed.
            Currency currency = new Currency()

                    .setDate(LocalDate.now().toString())
                    .setValuteId("-")
                    .setNumCode(643)
                    .setCharCode("RUB")
                    .setNominal(1)
                    .setName("Российский рубль")
                    .setValue("1");

            currencies.add(currency);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }
}
