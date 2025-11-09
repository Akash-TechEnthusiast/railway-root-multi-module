package com.india.railway.service.elastic;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import com.india.railway.model.elastic.Product_Elastic;
import com.india.railway.repository.elastic.ElasticProductRepository;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

@Service
public class ProductService {

        @Autowired
        ElasticProductRepository elasticproductRepository;

        @Autowired
        private ElasticsearchOperations elasticsearchOperations;

        public List<Product_Elastic> fuzzyMultiFieldSearch(String keyword, String keyword1) {
                // Fuzzy search across multiple fields
                Query query = MultiMatchQuery.of(m -> m
                                .query(keyword)
                                .fields("name", "description") // Add any fields you want here
                                .fuzziness("AUTO"))._toQuery();

                NativeQuery nativeQuery = NativeQuery.builder()
                                .withQuery(query)
                                .build();

                SearchHits<Product_Elastic> hits = elasticsearchOperations.search(nativeQuery, Product_Elastic.class);

                return hits.getSearchHits()
                                .stream()
                                .map(hit -> hit.getContent())
                                .toList();
        }

        public List<Product_Elastic> fuzzySearchByMultipleNames(String name1, String name2) {
                Query fuzzyQuery1 = MatchQuery.of(m -> m
                                .field("name")
                                .query(name1)
                                .fuzziness("AUTO"))._toQuery();

                Query fuzzyQuery2 = MatchQuery.of(m -> m
                                .field("description")
                                .query(name2)
                                .fuzziness("AUTO"))._toQuery();

                Query finalQuery = co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery.of(b -> b
                                .should(fuzzyQuery1)
                                .should(fuzzyQuery2))._toQuery();

                NativeQuery searchQuery = NativeQuery.builder()
                                .withQuery(finalQuery)
                                .build();

                SearchHits<Product_Elastic> hits = elasticsearchOperations.search(searchQuery, Product_Elastic.class);

                return hits.getSearchHits().stream()
                                .map(hit -> hit.getContent())
                                .toList();
        }

        public Iterable<Product_Elastic> getAllProducts() {
                return elasticproductRepository.findAll();
        }

        public List<Product_Elastic> searchProductsByName(String name) {
                return elasticproductRepository.findByName(name);
        }

        public void deleteAll() {
                elasticproductRepository.deleteAll();
        }

        public List<String> getAutoSuggestionsOnName(String prefix) {

                NativeQuery query = NativeQuery.builder()
                                .withQuery(q -> q
                                                .matchPhrasePrefix(m -> m
                                                                .field("name") // ✅ Changed from "suggestions" to "name"
                                                                .query(prefix)))
                                .build();

                SearchHits<Product_Elastic> searchHits = elasticsearchOperations.search(query, Product_Elastic.class);

                List<String> suggestions = new ArrayList<>();
                searchHits.forEach(hit -> suggestions.add(hit.getContent().getName())); // ✅ Extract name field

                return suggestions;
        }

        public List<String> getAutoSuggestions(String prefix) {

                NativeQuery query = NativeQuery.builder()
                                .withQuery(q -> q
                                                .matchPhrasePrefix(m -> m
                                                                .field("suggestions")
                                                                .query(prefix)))
                                .build();

                SearchHits<Product_Elastic> searchHits = elasticsearchOperations.search(query, Product_Elastic.class);

                List<String> suggestions = new ArrayList<>();
                searchHits.forEach(hit -> suggestions.addAll(hit.getContent().getSuggestions()));

                return suggestions;

        }

        /*
         * Explanation
         * 
         * NativeQuery → Used to build Elasticsearch queries in Spring Data
         * Elasticsearch.
         * Query → Represents the search query.
         * SearchHits → Stores the search results.
         * QueryBuilders → Helps in constructing Elasticsearch queries.
         * 
         * NativeQuery.builder() → Starts building a native Elasticsearch query.
         * .withQuery(q -> q.matchPhrasePrefix(...))
         * matchPhrasePrefix → Searches for words starting with the given prefix.
         * field("suggestions") → Searches in the suggestions field.
         * query(prefix) → Uses the user's input to find matching suggestions.
         * .build() → Finalizes and creates the query.
         * 
         * 
         * 
         * 
         * 
         * Creates a list to store suggestions.
         * Iterates through search results (searchHits).
         * Extracts the suggestions field from each product.
         * Adds them to the list.
         */

}
