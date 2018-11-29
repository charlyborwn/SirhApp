package org.primefaces.ultima.view;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ColumnResizeEvent;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import org.primefaces.ultima.domain.Documents;

public class DocumentsController implements Serializable {
	
	private static final Logger logger = Logger.getLogger(DocumentsController.class.getName());

	private TreeNode root;
    
    private TreeNode selectedNode;
    
    private TreeNode[] selectedNodes;

	private Documents selectedDocuments;
	
	public DocumentsController() {
		root = new DefaultTreeNode(new Documents("Files", "-", "Folder"), null);
		
		TreeNode documents = new DefaultTreeNode(new Documents("Documents", "-", "Folder"), root);
		TreeNode pictures = new DefaultTreeNode(new Documents("Pictures", "-", "Folder"), root);
		TreeNode movies = new DefaultTreeNode(new Documents("Movies", "-", "Folder"), root);
		
		TreeNode work = new DefaultTreeNode(new Documents("Work", "-", "Folder"), documents);
		TreeNode primefaces = new DefaultTreeNode(new Documents("PrimeFaces", "-", "Folder"), documents);
		
		//Documents
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
		
		TreeNode scarface = new DefaultTreeNode("mp3", new Documents("Scarface", "15 GB", "Movie File"), pacino);
		TreeNode carlitosWay = new DefaultTreeNode("mp3", new Documents("Carlitos' Way", "24 GB", "Movie File"), pacino);
		
		TreeNode goodfellas = new DefaultTreeNode("mp3", new Documents("Goodfellas", "23 GB", "Movie File"), deniro);
		TreeNode untouchables = new DefaultTreeNode("mp3", new Documents("Untouchables", "17 GB", "Movie File"), deniro);
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public Documents getSelectedDocuments() {
		return selectedDocuments;
	}

	public void setSelectedDocuments(Documents selectedDocuments) {
		this.selectedDocuments = selectedDocuments;
	}

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }
    
    public void onNodeExpand(NodeExpandEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void onNodeCollapse(NodeCollapseEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}
    
	public void onNodeSelect(NodeSelectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
		
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

    public void onNodeUnselect(NodeUnselectEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}
    
    public void onResize(ColumnResizeEvent event) {
        FacesMessage msg = new FacesMessage("Column " + event.getColumn().getClientId() + " resized", "W:" + event.getWidth() + ", H:" + event.getHeight());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void deleteNode() {
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);
        
        selectedNode = null;
    }
}