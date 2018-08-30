int flag = 0;
int a,b;
void pthread_create();
void pthread_join(int thread);
void f(int param)
{
	if(flag)
	{
		a++;
	}
	b = a;
}
int main()
{
	a = 1;
	int thread;
	pthread_create(&thread,0,f,&a);
	f(a);
	pthread_join(thread);
	return 0;
}
