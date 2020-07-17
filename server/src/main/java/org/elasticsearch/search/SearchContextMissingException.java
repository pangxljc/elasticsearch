/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.search;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.internal.SearchContextId;

import java.io.IOException;

public class SearchContextMissingException extends ElasticsearchException {

    private final SearchContextId contextId;

    public SearchContextMissingException(SearchContextId contextId) {
        super("No search context found for id [" + contextId.getId() + "]");
        this.contextId = contextId;
    }

    public SearchContextId contextId() {
        return this.contextId;
    }

    @Override
    public RestStatus status() {
        return RestStatus.NOT_FOUND;
    }

    public SearchContextMissingException(StreamInput in) throws IOException{
        super(in);
        contextId = new SearchContextId(in);
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        contextId.writeTo(out);
    }
}
