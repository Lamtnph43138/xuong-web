/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Model.NguoiHoc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import thiduanmau.Db.DBConnect;


public class NguoiHocRepo {
    Connection conn;

    public NguoiHocRepo() {
        this.conn = DBConnect.getConnection();
    }
  public List<NguoiHoc> fillData(){
      String sql = "select MaNH,HoTen,NgaySinh,DienThoai,Email,GhiChu from NguoiHoc";
      List<NguoiHoc> listNh = new ArrayList<>();
      try {
          PreparedStatement ps = this.conn.prepareStatement(sql);
          ps.execute();
          ResultSet rs = ps.getResultSet();
          while (rs.next()) {              
              int manh = rs.getInt("MaNH");
              String tennh = rs.getString("HoTen");
              Date ngays = rs.getDate("NgaySinh");
              String dt = rs.getString("DienThoai");
              String emaild = rs.getString("Email");
              String ghic = rs.getString("GhiChu");
              NguoiHoc nh = new NguoiHoc(manh, dt, ngays, dt, emaild, ghic);
              listNh.add(nh);
          }
      } catch (Exception e) {
      }
        return listNh;
              
  }
  
  public void add(NguoiHoc nh) {
        String sql = "INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, DienThoai, Email, GhiChu) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql) ;
            
          ps.setInt(1, nh.getMa());
          ps.setString(2, nh.getHoTen());
          ps.setDate(3,new Date(nh.getNgaySinh().getTime()));
          ps.setString(4, nh.getDienThoai());
          ps.setString(5, nh.getEmail());
          ps.setString(6, nh.getGhiChu());   
          ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();  // Ghi log lỗi, bạn có thể sử dụng một framework log như SLF4J
        }
    }
}
