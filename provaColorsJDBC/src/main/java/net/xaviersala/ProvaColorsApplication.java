package net.xaviersala;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProvaColorsApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(ProvaColorsApplication.class, args);
    }

    @Autowired
    ColorsRepository colorsRepository;
    
    @Override
    public void run(String... arg0) throws Exception {
      
      List<Color> colors = colorsRepository.findAll();
      
      for(Color c: colors) {
        System.out.println(c);
      }
      
      // Ara n'afegiré un
      Color c = new Color("vermell", "rojo", "rouge", "red");
      
      colorsRepository.afegirColor(c);
      
      Color d = colorsRepository.findColor("blau");
      if (d == null) {
        System.out.println("No hi ha blau");
      } else {
        System.out.println("Blau!:" + d);
      }
      
      
    }
}
