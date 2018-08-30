int flag = 0;
int *a,b;
void pthread_create();
void pthread_join(int thread);
void pthread_mutex_lock(int a);
void pthread_mutex_unlock(int a);
void f(int param)
{
	int local;
	if(flag)
	{
			pthread_mutex_lock(&a);
			local = *a;
			pthread_mutex_unlock(&a);
	}
}
int main()
{
	a = (int*)malloc(sizeof(int));
	int thread;
	pthread_create(&thread,0,f,(&b));
	pthread_join(thread);
	free(a);
	return 0;
}
