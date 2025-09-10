typedef char* string;

typedef struct {
	string tokens[256];
	int top;
} stack;

void createStack(stack *S);
void push(stack *S, string token);
char* pop(stack *S);
char* top(stack *S);
int stackEmpty(stack *S);
int stackFull(stack *S);
