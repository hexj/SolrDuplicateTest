/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riemersebastian.solrduplicate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 *
 * @author sr
 */
public class SolrDuplicateTest {

	private HttpSolrClient solrClient = new HttpSolrClient("http://localhost:8983/solr/duplicateTestCore");

	static final Logger LOGGER = Logger.getLogger(SolrDuplicateTest.class.getName());

	private void deleteAllIndexData() {
		LOGGER.info("Clear all existing data on index ... ");
		try {
			solrClient.deleteByQuery("*:*");
			solrClient.commit();
		} catch (SolrServerException | IOException ex) {
			LOGGER.log(Level.SEVERE, "Exception while deleteing index data! ", ex);
		}
	}

	private void createParentDocWithoutChild() {
		LOGGER.info("First create a parent document without a child ... ");
		SolrInputDocument parentDoc = new SolrInputDocument();
		parentDoc.addField("id", "parent_1");
		parentDoc.addField("name_s", "Sarah Connor");
		parentDoc.addField("blockJoinId", "1");

		try {
			solrClient.add(parentDoc);
			solrClient.commit();
			LOGGER.info("... and commit it on the index.");
		} catch (SolrServerException | IOException ex) {
			LOGGER.log(Level.SEVERE, "Exception while adding parent doc!", ex);
		}
	}

	private void updateExistingParentDocWithChild() {
		LOGGER.info("Then, update the existing parent document, this time with a child document assigned ... ");
		SolrInputDocument parentDocUpdateing = new SolrInputDocument();
		parentDocUpdateing.addField("id", "parent_1");
		parentDocUpdateing.addField("name_s", "Sarah Connor");
		parentDocUpdateing.addField("blockJoinId", "1");

		SolrInputDocument childDoc = new SolrInputDocument();
		childDoc.addField("id", "child_1");
		childDoc.addField("name_s", "John Connor");
		childDoc.addField("blockJoinId", "1");

		parentDocUpdateing.addChildDocument(childDoc);

		try {
			solrClient.add(parentDocUpdateing);
			solrClient.commit();
			LOGGER.info("... and commit it on the index.");
		} catch (SolrServerException | IOException ex) {
			LOGGER.log(Level.SEVERE, "Exception while updating parent doc!", ex);
		}
	}

	private void executeQueryAndShowResults() {
		LOGGER.info("List all documents with id = 'parent_1' ");
		try {
			SolrQuery query = new SolrQuery();
			query.add("q", "*:*");
			query.add("fq", "id:parent_1");
			QueryResponse response = solrClient.query(query);
			SolrDocumentList results = response.getResults();
			for (SolrDocument doc : results) {
				LOGGER.info("Found document with id = '" + doc.get("id") + "'");
			}
			LOGGER.info("Found a total of " + results.getNumFound() + " documents having id='parent_1'.");
		} catch (SolrServerException | IOException ex) {
			LOGGER.log(Level.SEVERE, "Exception while querying index!", ex);
		}
	}

	public static void main(String args[]) {

		SolrDuplicateTest solrDuplicateTest = new SolrDuplicateTest();

		solrDuplicateTest.LOGGER.info("Running SolrDuplicateTest ... ");
		solrDuplicateTest.deleteAllIndexData();
		solrDuplicateTest.createParentDocWithoutChild();
		solrDuplicateTest.updateExistingParentDocWithChild();
		solrDuplicateTest.executeQueryAndShowResults();

	}
}
