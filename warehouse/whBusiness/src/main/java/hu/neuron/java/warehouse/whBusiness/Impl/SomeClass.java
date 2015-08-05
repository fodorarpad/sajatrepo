package hu.neuron.java.warehouse.whBusiness.Impl;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

@Stateless
@LocalBean
@TransactionAttribute
public class SomeClass {
//	private static final Logger logger = Logger.getLogger(SomeClass.class);

	@EJB
//	public SomeDaoLocal someDAO;

	public void someMethod() {
	}

}
