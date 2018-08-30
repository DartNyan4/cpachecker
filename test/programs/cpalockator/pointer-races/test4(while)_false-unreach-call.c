int flag = 0;
int *a,b,c;
void pthread_create();
void pthread_join(int thread);
void f(int param)
{
	int i = 5;
	while(i == 5)
	{
		free(a);
		i = 0;
	}
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
