/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.ultima.view.data.treetable;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.TreeNode;
import org.primefaces.ultima.domain.Documents;
import org.primefaces.ultima.service.DocumentService;

@Named("ttBasicView")
@ViewScoped
public class BasicView implements Serializable {

    private TreeNode root;

    private Documents selectedDocuments;

    @Inject
    private DocumentService documentService;

    @PostConstruct
    public void init() {
        root = documentService.createDocuments();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setService(DocumentService service) {
        this.documentService = service;
    }

    public Documents getSelectedDocuments() {
        return selectedDocuments;
    }

    public void setSelectedDocuments(Documents selectedDocuments) {
        this.selectedDocuments = selectedDocuments;
    }
}
