package models;

import play.db.jpa.GenericModel;
import play.db.jpa.JPABase;
import play.db.jpa.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * User: joeljohnson
 * Date: 2/11/12
 * Time: 9:14 PM
 */
@MappedSuperclass
public class BaseModel extends GenericModel {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	protected String id;

	public String getId() {
		return id;
	}

	@Override
	public Object _key() {
		return getId();
	}
}
