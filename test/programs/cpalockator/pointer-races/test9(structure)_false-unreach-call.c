int flag = 0;
char *a,b,c;
void pthread_create();
void pthread_join(int thread);

typedef struct m
{
	unsigned int n_cols;
	unsigned int n_rows;
	int* body;
} Matrix;

Matrix* M;

void f(int param)
{
	free(M);
}

int main()
{
	M = (Matrix*)malloc(sizeof(Matrix));
	M->n_cols = 10;
	M->n_rows = 10;
	M->body = (int*)malloc(sizeof(int)*M->n_cols*M->n_rows);
	int thread;
	pthread_create(&thread,0,f,(&b));
	c = M->n_cols;
	pthread_join(thread);
	return 0;
}
