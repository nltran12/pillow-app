package pillow.tools;

import pillow.dal.*;
import pillow.model.*;

import java.sql.SQLException;
import java.util.List;

public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		LandlordReviewsDao landlordReviewsDao = LandlordReviewsDao.getInstance();
		LandlordsDao landlordsDao = LandlordsDao.getInstance();
		PropertiesDao propertiesDao = PropertiesDao.getInstance();
		ReferenceDao referenceDao = ReferenceDao.getInstance();
		ReservationsDao reservationsDao = ReservationsDao.getInstance();
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		TenantsDao tenantsDao = TenantsDao.getInstance();
		UsersDao usersDao = UsersDao.getInstance();

		// TenantsDao tests
		// delete
		Tenants tenant = tenantsDao.getTenantsFromUserName("Samantha");
		Tenants result = tenantsDao.delete(tenant);
		System.out.println(result);
		
		
		// PropertiesDao tests
		// getPropertiesById
		Properties property = propertiesDao.getPropertiesById(22);
    System.out.format("Reading property: id:%s title:%s \n", property.getPropertyId(),
        property.getTitle());
    
    // getPropertiesByMinRating
    List<Properties> properties = propertiesDao.getPropertiesByMinRating(3.9f);
    for (Properties prop : properties) {
      System.out.format("Reading property: id:%s title:%s \n", prop.getPropertyId(),
          prop.getTitle());
    }
    
    // getPropertiesByMaxRent
    properties = propertiesDao.getPropertiesByMaxRent(200f);
    for (Properties prop : properties) {
      System.out.format("Reading property: id:%s title:%s \n", prop.getPropertyId(),
          prop.getTitle());
    }
    
    // getPropertiesByNeighborhoodAndMinRating
    properties = propertiesDao.getPropertiesByNeighborhoodAndMaxRent("Capitol Hill", 2.5f);
    for (Properties prop : properties) {
      System.out.format("Reading property: id:%s title:%s \n", prop.getPropertyId(),
          prop.getTitle());
    }
    
    // getPropertiesByRoomType
    // only loops through and prints the first 10 results
    properties = propertiesDao.getPropertiesByRoomType("Shared room");
    for (int i = 0; i < 10; i++) {
      Properties prop = properties.get(i);
      System.out.format("Reading property: resNum:%s id:%s title:%s \n", i, prop.getPropertyId(),
          prop.getTitle());
    }


	}
}
