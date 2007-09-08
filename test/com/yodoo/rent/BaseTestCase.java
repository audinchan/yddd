package com.yodoo.rent;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public abstract class BaseTestCase extends
    AbstractTransactionalSpringContextTests
{

	DriverManagerDataSource dataSource;


	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
	}

    @Override
    protected String[] getConfigLocations()
    {
        this.setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
        		"file:WebContent\\WEB-INF\\applicationContext-*.xml",
        		"file:WebContent\\WEB-INF\\datasource-context-test.xml"
        		};
    }
    
	@Override
	protected void onSetUpInTransaction() throws Exception {
		IDatabaseConnection connection = new DatabaseConnection(
				dataSource.getConnection());
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
		XmlDataSet dataset = new XmlDataSet(
				applicationContext.getResource("file:conf\\test-data.xml").getInputStream());
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
		super.onSetUpInTransaction();
	}

}
