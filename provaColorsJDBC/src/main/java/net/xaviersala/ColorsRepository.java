package net.xaviersala;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ColorsRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;
  
  public List<Color> findAll() {
    
    return jdbcTemplate.query("SELECT * FROM Color", 
        new RowMapper<Color>() {
          @Override
          public Color mapRow(ResultSet rs, int num) throws SQLException {
            Color c = new Color(rs.getString("catala"),
                                rs.getString("castella"),
                                rs.getString("frances"),
                                rs.getString("angles"));
            return c;
          }
      });
         
  }
  
  Color findColor(String nom) {
    Color color = jdbcTemplate.queryForObject("SELECT * FROM Color WHERE catala=?", 
        new Object[]{ nom }  , 
        new RowMapper<Color>() {

          @Override
          public Color mapRow(ResultSet rs, int arg1) throws SQLException {
            Color c = new Color(rs.getString("catala"),
                                rs.getString("castella"), 
                                rs.getString("frances"),              
                                rs.getString("angles"));
            return c;
          } 
      
        });
    return color;
  }
  
  public void afegirColor(Color c) {
    jdbcTemplate.update("INSERT INTO Color (catala, castella, frances, angles) VALUES (?,?,?,?)",
         c.getCatala(), c.getCastella(), c.getFrances(), c.getAngles());
  }
}
