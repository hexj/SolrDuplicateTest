To run this test:

1) Copy the "duplicateTestCore" folder in your solr core home directory, where it gets auto-detected
2) Open the attached sources (I used netbeans to create the standalone test)
3) Run the main-class SolrDuplicateTest.java

Result should be similar to this, alternatively you should also be able to see the issue using the web-client,
e.g. http://localhost:8983/solr/#/duplicateTestCore/query and just run a q=*:* query there.

If you have any questions do not hesitate to mail me: sr@littera.eu

```                                                                        
------------------------------------------------------------------------
Building SolrDuplicateTest 1.0-SNAPSHOT
------------------------------------------------------------------------

--- exec-maven-plugin:1.2.1:exec (default-cli) @ SolrDuplicateTest ---
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Mär 08, 2016 7:36:20 PM com.riemersebastian.solrduplicate.SolrDuplicateTest main
INFORMATION: Running SolrDuplicateTest ... 
Mär 08, 2016 7:36:20 PM com.riemersebastian.solrduplicate.SolrDuplicateTest deleteAllIndexData
INFORMATION: Clear all existing data on index ... 
Mär 08, 2016 7:36:20 PM com.riemersebastian.solrduplicate.SolrDuplicateTest createParentDocWithoutChild
INFORMATION: First create a parent document without a child ... 
Mär 08, 2016 7:36:20 PM com.riemersebastian.solrduplicate.SolrDuplicateTest createParentDocWithoutChild
INFORMATION: ... and commit it on the index.
Mär 08, 2016 7:36:20 PM com.riemersebastian.solrduplicate.SolrDuplicateTest updateExistingParentDocWithChild
INFORMATION: Then, update the existing parent document, this time with a child document assigned ... 
Mär 08, 2016 7:36:21 PM com.riemersebastian.solrduplicate.SolrDuplicateTest updateExistingParentDocWithChild
INFORMATION: ... and commit it on the index.
Mär 08, 2016 7:36:21 PM com.riemersebastian.solrduplicate.SolrDuplicateTest executeQueryAndShowResults
INFORMATION: List all documents with id = 'parent_1' 
Mär 08, 2016 7:36:21 PM com.riemersebastian.solrduplicate.SolrDuplicateTest executeQueryAndShowResults
INFORMATION: Found document with id = parent_1
Mär 08, 2016 7:36:21 PM com.riemersebastian.solrduplicate.SolrDuplicateTest executeQueryAndShowResults
INFORMATION: Found document with id = parent_1
Mär 08, 2016 7:36:21 PM com.riemersebastian.solrduplicate.SolrDuplicateTest executeQueryAndShowResults
INFORMATION: Found a total of 2 documents having id=parent_1.
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time: 1.657s
Finished at: Tue Mar 08 19:36:21 CET 2016
Final Memory: 6M/245M
------------------------------------------------------------------------
```