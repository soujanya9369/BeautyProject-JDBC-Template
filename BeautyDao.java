package trg.talentsprint.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BeautyDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(BeautyProdutcs bp) {
		String sql = "insert into beauty(name,description,price) values ('" + bp.getName() + "'," + bp.getDescription()
				+ ",'" + bp.getPrice() + "')";
		return template.update(sql);
	}

	public int update(BeautyProdutcs bp) {

		String sql = "update beauty set name='" + bp.getName() + "', description=" + bp.getDescription() + ",price='"
				+ bp.getPrice() + "' where id=" + bp.getId() + "";
		return template.update(sql);
	}

	public int delete(int id) {
		String sql = "delete from beauty where id="+id+"";
		 return template.update(sql);
	}

	public BeautyProdutcs getBeautyProductsId(int id) {
		String sql="select * from beauty where id=?";
		return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<BeautyProdutcs>(BeautyProdutcs.class));
	}

	/*public  List<BeautyProdutcs> getBeautyProductsList(){
		 return template.query("select * from beauty",new RowMapper<BeautyProdutcs>(){ 
			  public BeautyProdutcs mapRow(ResultSet rs, int row) throws SQLException { 
				  BeautyProdutcs bp=new BeautyProdutcs();
				  bp.setId(rs.getInt(1));
					bp.setName(rs.getString(2));
					bp.setDescription(rs.getString(3));
					bp.setPrice(rs.getInt(4));
					return bp;
				  
			  }
		 });*/
	
	public  List<BeautyProdutcs> getBeautyProductsList() throws ClassNotFoundException, SQLException {
		List<BeautyProdutcs> list = new ArrayList<BeautyProdutcs>();
		Connection con = DaoUtility.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from beauty");
		BeautyProdutcs bp;
		while (rs.next()) {
			bp = new BeautyProdutcs();
			bp.setId(rs.getInt(1));
			bp.setName(rs.getString(2));
			bp.setDescription(rs.getString(3));
			bp.setPrice(rs.getInt(4));
			list.add(bp);

		}

		return list;

	}
		
	}

