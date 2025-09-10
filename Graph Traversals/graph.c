#include "queue.c"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
This function initializes all the variables in a graph structure
to zero
@param numVertices - number of unique vertices in a graph
@return *graph - returns a graph with values initialized
*/
graph* createGraph(int numVertices){
	int i, j;
	//allocates memory for a graph structure
	graph* g = (graph*) malloc(sizeof(graph));
	//actual number will be added later when vertices are added
	g->numVertices = 0;
	
	//sets up the adjacency matrix and the visited array by initializing it to 0
	for(i = 0; i < numVertices; i++){
		for(j = 0; j < numVertices; j++){
			g->adjMatrix[i][j] = 0;
		}
		g->visited[i] = 0;
	}
	return g;
}

/*
Gets the index of a vertex from the list of vertices
@param graph - graph structure pointer
@param vertex - vertex to be searched for
@return i - returns the index of the vertex if found
@return -1 - returns -1 if the vertex is not found
*/
int getIndex(graph* graph, String vertex){
	int i;
	
	//Linear search to find the index of the vertex
	for(i = 0; i < graph->numVertices; i++){
		if(strcmp(graph->vertices[i], vertex) == 0){
			return i;
		}
	}
	return -1;
}

/*
Adds a vertex to the graph
@param graph - graph structure pointer
@param vertex - vertex to be added
@return - if number of vertices reaches max amount of vertices
*/
void addVertex(graph* graph, String vertex){
	//checks if numVertices variable reached the maximum
	if (graph->numVertices >= MAX_VERTICES) {
		printf("Cannot Add Vertex. Max Number of Vertices Reached\n");
		//exits the function
		return;
	}
	//adds the vertex to the list of vertices
	strcpy(graph->vertices[graph->numVertices], vertex);
	//increases the num of vertices as a vertex is succesfully added
	graph->numVertices++;
}

/*
Adds a vertex adjacent to the main vertex using 
the adjacency matrix to store the information to help
determine if this vertex is adjacent to another
@param graph - graph structure pointer
@param from - the main vertex
@param to - vertex that is adjacent to the main vertex
*/
void addEdge(graph* graph, String from, String to){
	//gets the index of the main vertex
	int fromIndex = getIndex(graph, from);
	//gets the index of the adjacent vertex
	int toIndex = getIndex(graph, to);
	
	//checks if the vertices are not found
	if(fromIndex != -1 && toIndex != -1){
		//adds 1 to the adjacency matrix to indicate that the vertices are adjacent
		graph->adjMatrix[fromIndex][toIndex] = 1;
		graph->adjMatrix[toIndex][fromIndex] = 1;
	}
}

