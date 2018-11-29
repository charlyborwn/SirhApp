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
package org.primefaces.ultima.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.ultima.domain.Documents;

@Named
@ApplicationScoped
public class DocumentService {

    public TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Documents("Files", "-", "Folder"), null);

        TreeNode documents = new DefaultTreeNode(new Documents("Document", "-", "Folder"), root);
        TreeNode pictures = new DefaultTreeNode(new Documents("Pictures", "-", "Folder"), root);
        TreeNode movies = new DefaultTreeNode(new Documents("Movies", "-", "Folder"), root);

        TreeNode work = new DefaultTreeNode(new Documents("Work", "-", "Folder"), documents);
        TreeNode primefaces = new DefaultTreeNode(new Documents("PrimeFaces", "-", "Folder"), documents);

        //Document
        TreeNode expenses = new DefaultTreeNode("document", new Documents("Expenses.doc", "30 KB", "Word Documents"), work);
        TreeNode resume = new DefaultTreeNode("document", new Documents("Resume.doc", "10 KB", "Word Documents"), work);
        TreeNode refdoc = new DefaultTreeNode("document", new Documents("RefDoc.pages", "40 KB", "Pages Documents"), primefaces);

        //Pictures
        TreeNode barca = new DefaultTreeNode("picture", new Documents("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
        TreeNode primelogo = new DefaultTreeNode("picture", new Documents("logo.jpg", "45 KB", "JPEG Image"), pictures);
        TreeNode optimus = new DefaultTreeNode("picture", new Documents("optimusprime.png", "96 KB", "PNG Image"), pictures);

        //Movies
        TreeNode pacino = new DefaultTreeNode(new Documents("Al Pacino", "-", "Folder"), movies);
        TreeNode deniro = new DefaultTreeNode(new Documents("Robert De Niro", "-", "Folder"), movies);

        TreeNode scarface = new DefaultTreeNode("movie", new Documents("Scarface", "15 GB", "Movie File"), pacino);
        TreeNode carlitosWay = new DefaultTreeNode("movie", new Documents("Carlitos' Way", "24 GB", "Movie File"), pacino);

        TreeNode goodfellas = new DefaultTreeNode("movie", new Documents("Goodfellas", "23 GB", "Movie File"), deniro);
        TreeNode untouchables = new DefaultTreeNode("movie", new Documents("Untouchables", "17 GB", "Movie File"), deniro);

        return root;
    }

    public TreeNode createCheckboxDocuments() {
        TreeNode root = new CheckboxTreeNode(new Documents("Files", "-", "Folder"), null);

        TreeNode documents = new CheckboxTreeNode(new Documents("Document", "-", "Folder"), root);
        TreeNode pictures = new CheckboxTreeNode(new Documents("Pictures", "-", "Folder"), root);
        TreeNode movies = new CheckboxTreeNode(new Documents("Movies", "-", "Folder"), root);

        TreeNode work = new CheckboxTreeNode(new Documents("Work", "-", "Folder"), documents);
        TreeNode primefaces = new CheckboxTreeNode(new Documents("PrimeFaces", "-", "Folder"), documents);

        //Document
        TreeNode expenses = new CheckboxTreeNode("document", new Documents("Expenses.doc", "30 KB", "Word Documents"), work);
        TreeNode resume = new CheckboxTreeNode("document", new Documents("Resume.doc", "10 KB", "Word Documents"), work);
        TreeNode refdoc = new CheckboxTreeNode("document", new Documents("RefDoc.pages", "40 KB", "Pages Documents"), primefaces);

        //Pictures
        TreeNode barca = new CheckboxTreeNode("picture", new Documents("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
        TreeNode primelogo = new CheckboxTreeNode("picture", new Documents("logo.jpg", "45 KB", "JPEG Image"), pictures);
        TreeNode optimus = new CheckboxTreeNode("picture", new Documents("optimusprime.png", "96 KB", "PNG Image"), pictures);

        //Movies
        TreeNode pacino = new CheckboxTreeNode(new Documents("Al Pacino", "-", "Folder"), movies);
        TreeNode deniro = new CheckboxTreeNode(new Documents("Robert De Niro", "-", "Folder"), movies);

        TreeNode scarface = new CheckboxTreeNode("movie", new Documents("Scarface", "15 GB", "Movie File"), pacino);
        TreeNode carlitosWay = new CheckboxTreeNode("movie", new Documents("Carlitos' Way", "24 GB", "Movie File"), pacino);

        TreeNode goodfellas = new CheckboxTreeNode("movie", new Documents("Goodfellas", "23 GB", "Movie File"), deniro);
        TreeNode untouchables = new CheckboxTreeNode("movie", new Documents("Untouchables", "17 GB", "Movie File"), deniro);

        return root;
    }
}
