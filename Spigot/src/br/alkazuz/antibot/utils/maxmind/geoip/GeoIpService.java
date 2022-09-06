package br.alkazuz.antibot.utils.maxmind.geoip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;

public class GeoIpService
{
    private LookupService lookupService;
    private Thread downloadTask;
    private final File dataFile;
    
    public GeoIpService(final File dataFolder) {
        this.dataFile = new File(dataFolder, "GeoIP.dat");
        this.isDataAvailable();
    }
    
    public GeoIpService(final File dataFolder, final LookupService lookupService) {
        this.dataFile = dataFolder;
        this.lookupService = lookupService;
    }
    
    public synchronized boolean isDataAvailable() {
        if (this.downloadTask != null && this.downloadTask.isAlive()) {
            return false;
        }
        if (this.lookupService != null) {
            return true;
        }
        if (this.dataFile.exists()) {
            final boolean dataIsOld = System.currentTimeMillis() - this.dataFile.lastModified() > TimeUnit.DAYS.toMillis(30L);
            if (!dataIsOld) {
                try {
                    this.lookupService = new LookupService(this.dataFile, 1);
                    return true;
                }
                catch (IOException e) {
                	 SpartanAntiBot.theInstance().sendConcoleMessage("Failed to load GeoLiteAPI database");
                    return false;
                }
            }
            dataFile.delete();
        }
        (this.downloadTask = this.createDownloadTask()).start();
        return false;
    }
    
    public Thread createDownloadTask() {
    	SpartanAntiBot.theInstance().sendConcoleMessage("Realizando o download do banco de dados de paises...");
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final URL downloadUrl = new URL("https://advanced-bot.tk/bot/api/GeoIP.dat");
                    final URLConnection conn = downloadUrl.openConnection();
                    conn.setConnectTimeout(10000);
                    conn.connect();
                    InputStream input = conn.getInputStream();
                    if (conn.getURL().toString().endsWith(".gz")) {
                        input = new GZIPInputStream(input);
                    }
                    final OutputStream output = new FileOutputStream(GeoIpService.this.dataFile);
                    final byte[] buffer = new byte[2048];
                    for (int length = input.read(buffer); length >= 0; length = input.read(buffer)) {
                        output.write(buffer, 0, length);
                    }
                    output.close();
                    input.close();
                }
                catch (IOException e) {
                    SpartanAntiBot.theInstance().sendConcoleMessage("§cCould not download GeoLiteAPI database");
                }
            }
        });
    }
    
    public String getCountryCode(final String ip) {
        if (!"127.0.0.1".equals(ip) && this.isDataAvailable()) {
            return this.lookupService.getCountry(ip).getCode();
        }
        return "--";
    }
    
    public String getCountryName(final String ip) {
        if (!"127.0.0.1".equals(ip) && this.isDataAvailable()) {
            return this.lookupService.getCountry(ip).getName();
        }
        return "N/A";
    }
}
