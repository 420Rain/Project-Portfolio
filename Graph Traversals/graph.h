//Maximum amount of vertices as mentioned in the discussion
#define MAX_VERTICES 30

//Amount of characters for each vertex
typedef char String[100];

//Structure for storing the contents in each line in the text file
typedef struct Row{
	String vertices[100];
	int vertexCount;
} Row;

//Structure for the graph
typedef struct graph{
	int adjMatrix[MAX_VERTICES][MAX_VERTICES];
	int numVertices;
	int visited[MAX_VERTICES];
	String vertices[MAX_VERTICES];
} graph;

//Function prototypes for the functions in graph.c
graph* createGraph(int numVertices);
int getIndex(graph* graph, String vertex);
void addVertex(graph* graph, String vertex);
void addEdge(graph* graph, String from, String to);
