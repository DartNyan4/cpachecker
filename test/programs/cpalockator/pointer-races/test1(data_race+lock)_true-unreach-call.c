int flag = 0;
int a,b;
void pthread_create();
void pthread_join(int thread);
void pthread_mutex_lock(int a);
void pthread_mutex_unlock(int a);
void f(int param)
{
	if(flag)
	{
		pthread_mutex_lock(&a);
		a++;
		pthread_mutex_unlock(&a);
	}
}
int main()
{
	a = 1;
	int thread;
	pthread_create(&thread,0,f,&a);
	pthread_mutex_lock(&a);
	b = a;
	pthread_mutex_unlock(&a);
	pthread_join(thread);
	return 0;
}
