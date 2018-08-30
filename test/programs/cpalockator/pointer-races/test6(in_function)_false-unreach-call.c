int flag = 1;
int *a,b,c;
void pthread_create();
void pthread_join(int thread);
void customfree()
{
	free(a);
}
void f(int param)
{
	customfree();
}
int main()
{
	a = (int*)malloc(sizeof(int));
	int thread;
	pthread_create(&thread,0,f,(&b));
	c = *a;
	pthread_join(thread);
	return 0;
}
