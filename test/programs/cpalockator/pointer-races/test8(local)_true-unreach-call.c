int flag = 1;
int b,c;
void pthread_create();
void pthread_join(int thread);
void f(int * param)
{
	if(flag)
	{
		free(param);
	}
}
int main()
{
	int *local = 1;
	local = (int*)malloc(sizeof(int));
	int thread;
	pthread_create(&thread,0,f,(local));
	c = *local;
	pthread_join(thread);
	return 0;
}
