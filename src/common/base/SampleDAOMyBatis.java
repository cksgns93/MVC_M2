package common.base;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SampleDAOMyBatis {

   //네임스페이스 지정. SampleMapper.xml에 지정된 namespace값과 동일해야 함
   private final String NS="common.base.SampleMapper";
   
   /**사용자 정의 메소드: SqlSessionFactory객체를 반환하는 메소드*/
   public SqlSessionFactory getSqlSessionFactory() {
      String resource="common/config/mybatis-config.xml"; 
      //설계도 역할을 하는 파일(mybatis환경설정 파일)
      InputStream is=null;
      try {
         is=Resources.getResourceAsStream(resource);
         SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder(); //건축가
         SqlSessionFactory factory=builder.build(is);
         return factory;         
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
      
   }//----------------------------
   public int selectTableCount() {
      SqlSessionFactory factory=this.getSqlSessionFactory();
      //공장을 통해 상품을 생산
      SqlSession session=factory.openSession();
      try {
      int count=session.selectOne(NS+".tableCount");
      System.out.println("count="+count);
      return count;
      }finally {
         if(session!=null) session.close();
      }
   }//----------------------------
   
}