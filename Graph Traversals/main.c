#include "traversal.c"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main(){
	String filename;
	
	//Asks the user of the desired filename
	printf("Input filename: ");
	scanf("%s", filename);
	
	FILE *fp;
	//Opens Filename inputted by the user
	fp = fopen(filename, "r");
	
	//Ends program if filename not found
	if (fp == NULL){
		printf("%s not found.", filename);
		fclose(fp);
		exit(0);
	}
	
	int i, j;
	int numVertices = 0;
	
	//Scans desired number of vertices
	fscanf(fp, "%d", &numVertices);
	
	//For storing the contents of each line in the file
	Row rows[numVertices]; 
	//Stores the original order of vertices in the text file
	String vertices[numVertices]; 
	
	for(i = 0; i < numVertices; i++){
		int j = 0;
		//Scans the contents of each line in the text file
		while(1){
			fscanf(fp, "%s", rows[i].vertices[j]);
			//Checks if the end of the line is reached
			if(strcmp(rows[i].vertices[j], "-1") == 0){
				break;
			}
			j++;
		}
		//Adds the main vertex to the list of vertices
		strcpy(vertices[i], rows[i].vertices[0]);
		rows[i].vertexCount = j;
	}
	
	fclose(fp);
	
	int min;
	Row temp;
	
	//Arranges the main vertices in alphabetical order through selection sort
	for (i = 0; i < numVertices - 1; i++){
		min = i;
		for (j = i + 1; j < numVertices; j++){
			//Checks if the main vertex is alphabetically before the other
			if (strcmp(rows[j].vertices[0], rows[min].vertices[0]) < 0){
				min = j;
            }
        }
        
        //Swaps the main vertices if they are not in alphabetical order
        if (min != i) {
            temp = rows[min];
            rows[min] = rows[i];
            rows[i] = temp;
        }
	}
	
	//Creates graph with the number of vertices
	graph* graph = NULL;
	graph = createGraph(numVertices);
	
	//Adds the main vertices to the graph
	for (i = 0; i < numVertices; i++){
		addVertex(graph, rows[i].vertices[0]);
		for (j = 1; j < rows[i].vertexCount; j++){
			//Adds the adjacent vertices of the main vertex to the adjacency matrix
            addEdge(graph, rows[i].vertices[0], rows[i].vertices[j]);
        }
	}
	
	//Creates queues for the BFS and DSF traversals
	queue bfsOfGraph;
	queue dfsOfGraph;
	createQueue(&bfsOfGraph);
	createQueue(&dfsOfGraph);
	
	String startVertex;
	//Asks the user for the starting vertex
	printf("Input start vertex for the traversal: ");
	scanf("%s", startVertex);
	
	//Handles casing of the input string
	startVertex[0] = toupper(startVertex[0]);
	for(i = 1; startVertex[i] != '\0'; i++){
		startVertex[i] = tolower(startVertex[i]);
	}
	
	//Checks if the vertex exists in the list of vertices in the graph
	if(getIndex(graph, startVertex) == -1){
		printf("Vertex %s not found.", startVertex);
		exit(0);
	}
	
	//Performs the BFS and DFS algotithms with the starting vertex	
	BFS(graph, startVertex, &bfsOfGraph);
	DFS(graph, startVertex, &dfsOfGraph);
	
	//Outputs the needed details to a text file
	fileOutput(*graph, &bfsOfGraph, &dfsOfGraph, vertices);
	
	return 0;
}

