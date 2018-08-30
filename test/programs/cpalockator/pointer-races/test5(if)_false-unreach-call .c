int flag = 0;
int *a,b,c;
void pthread_create();
void pthread_join(int thread);
void f(int param)
{
	flag = 1;
	if (flag)
	{
		free(a);
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
