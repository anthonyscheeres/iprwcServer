package main.java.nl.iipsen2server.controlllers;

import java.util.List;

class LogController {
	 /**
	 *
	 * @author Anthony Scheeres
	 *
	 */
	 public long createUserId2(List<String> list){
		 if (list==null) {
			 return 1;
		 }
		 long id = 1;
		 for (String databaseModel : list) {
			 if (id <= Integer.valueOf(databaseModel)) {
				 id = Integer.valueOf(databaseModel)+1;
			 }
		 }
		 return id;
	 }
	 
	 

}
