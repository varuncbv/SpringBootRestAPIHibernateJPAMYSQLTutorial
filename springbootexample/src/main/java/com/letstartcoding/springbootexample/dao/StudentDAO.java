package com.letstartcoding.springbootexample.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.letstartcoding.springbootexample.model.Student;

@Service
public class StudentDAO implements IStudentDAO {
	
	
	JdbcTemplate template;  
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}

	
	
	public void save(Student p) {
		
//String sql="insert into world.student(firstName,lastName,sex,dob,email,section,country,firstAttempt,subjects) values('"+p.getFirstName()+"','"+p.getLastName()+"','"+p.getSex()+"','"+p.getDob()+"','"+p.getEmail()+"','"+p.isFirstAttempt()+"','"+p.getCountry()+"',true,'"+convertListToDelimitedString(p.getSubjects())+"')"; 
		
		String sql="insert into student(firstName,lastName,email,sex,dob,section,country,firstAttempt,subjects) values('"+p.getFirstName()+"','"+p.getLastName()+"','"+p.getEmail()+"','"+p.getSex()+"','"+ConvertDate(p.getDob())+"','"+p.getSection()+"','"+p.getCountry()+"',"+p.isFirstAttempt()+",'"+convertListToDelimitedString(p.getSubjects())+"')";
		System.out.println(sql);
	     template.update(sql);  
	}
	
	private String convertListToDelimitedString(List<String> list) {

		String result = "";
		if (list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		return result;

	}
	
	public List<Student> getAllStudents() {
		
		
		return template.query("select * from student",new ResultSetExtractor<List<Student>>(){  
		    
		     public List<Student> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<Student> list=new ArrayList<Student>();  
		        while(rs.next()){  
		        Student e=new Student();  
		        e.setId(rs.getInt(1));  
		        e.setFirstName(rs.getString(2));  
		        e.setLastName(rs.getString(3));  
		        e.setSex(rs.getString(4));
		        e.setDob(rs.getDate(5));
		        e.setEmail(rs.getString(6));
		        e.setSection(rs.getString(7));  
		        e.setCountry(rs.getString(8));  
		        e.setFirstAttempt(rs.getBoolean(9));
		        e.setSubjects(convertDelimitedStringToList(rs.getString(10)));
		        
		        list.add(e);  
		        }  
		        return list;  
		        }  
		    });  
		  
		
		  }
	
	public List<Student> getStudentsByPage(int pageid, int total) {
		// TODO Auto-generated method stub
		 String sql="select * from Student limit "+(pageid-1)+","+total;  
		 return template.query(sql,new ResultSetExtractor<List<Student>>(){  
		    
		     public List<Student> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<Student> list=new ArrayList<Student>();  
		        
		        while(rs.next()){  
		        Student e=new Student();  
		        e.setId(rs.getInt(1));  
		        e.setFirstName(rs.getString(2));  
		        e.setLastName(rs.getString(3));  
		        e.setSex(rs.getString(4));
		        e.setDob(rs.getDate(5));
		        e.setEmail(rs.getString(6));
		        e.setSection(rs.getString(7));  
		        e.setCountry(rs.getString(8));  
		        e.setFirstAttempt(rs.getBoolean(9));
		        e.setSubjects(convertDelimitedStringToList(rs.getString(10)));
		        
		        list.add(e);  
		        }  
		        return list;  
		        }  
		    });  
		  }
	
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
				 return template.query("select * from student where ID="+id,new ResultSetExtractor<Student>(){  
					    
				     public Student extractData(ResultSet rs) throws SQLException,  
				            DataAccessException {  
				      
				    	 Student e=new Student();  
				        while(rs.next()){  
				       
				        	e.setId(rs.getInt(1));  
					        e.setFirstName(rs.getString(2));  
					        e.setLastName(rs.getString(3));  
					        e.setSex(rs.getString(4));
					        e.setDob(rs.getDate(5));
					        e.setEmail(rs.getString(6));
					        e.setSection(rs.getString(7));  
					        e.setCountry(rs.getString(8));  
					        e.setFirstAttempt(rs.getBoolean(9));
					        e.setSubjects(convertDelimitedStringToList(rs.getString(10)));     
				        }  
				        return e;  
				        }  
				    });  
			}
	
	private static List<String> convertDelimitedStringToList(String delimitedString) {

		List<String> result = new ArrayList<String>();

		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;

	}
	
	public String ConvertDate(Date date){
		
		
		   
		  
		 String formatedDate ="";
	    try {
	    	DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
			date = (Date)formatter.parse(date.toString());
			 Calendar cal = Calendar.getInstance();
			   
			    cal.setTime(date);
			    
			    formatedDate=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH) + 1)+"-"+cal.get(Calendar.DATE);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    
	    return formatedDate;

	   
	  }



	public int count() {
		String sql="select count(*) from Student";
		int count = template.queryForObject(sql,Integer.class);
		return count;
		  }



	public void update(Student p) {
		String sql="update Student set firstName='"+p.getFirstName()+"',lastName='"+p.getLastName()+"',sex='"+p.getSex()+"',dob='"+ConvertDate(p.getDob())+"',email='"+p.getEmail()+"',section='"+p.getSection()+"' ,country='"+p.getCountry()+"', firstAttempt="+p.isFirstAttempt()+",subjects='"+convertListToDelimitedString(p.getSubjects())+"' where ID="+p.getId()+"";
		System.out.println(sql);
       template.update(sql);  
	}



	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from Student where ID="+id+"";  
	    template.update(sql);  
		
	}



	public void delete() {
		// TODO Auto-generated method stub
		String sql="delete from Student where ID>0";  
	    template.update(sql); 
	}



	

	

}
