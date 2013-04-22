package com.pardot.rhombus.functional;

import static org.junit.Assert.*;

import com.datastax.driver.core.exceptions.InvalidConfigurationInQueryException;
import com.pardot.rhombus.helpers.TestHelpers;
import org.junit.Test;

import com.datastax.driver.core.Session;

import com.pardot.rhombus.ConnectionManager;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: michaelfrank
 * Date: 4/11/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class CassandraConnectionTest {

	@Test
	public void testKeyspaceCreate() throws IOException {
		ConnectionManager cm = new ConnectionManager(TestHelpers.getTestCassandraConfiguration());
		cm.buildCluster();
		Session session = cm.getEmptySession();
		assertNotNull(session);

		//Drop the functional keyspace if it exists
		try {
			session.execute("DROP KEYSPACE functional_create");
		} catch (InvalidConfigurationInQueryException e) {
			//Ignore
		}

		//Create the functional keyspace
		session.execute("CREATE KEYSPACE functional_create WITH replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }");

		//Change to our functional testing keyspace
		session.execute("USE functional_create");

		//Drop the functional keyspace
		try {
			session.execute("DROP KEYSPACE functional_create");
		} catch (InvalidConfigurationInQueryException e) {
			//Ignore
		}

		//Shutdown the session
		session.shutdown();

		//Teardown the connection manager
		cm.teardown();
	}


}