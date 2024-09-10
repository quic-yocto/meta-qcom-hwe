/**
 * Copyright (c) 2024 QUALCOMM Technologies Inc. All Rights Reserved.
 * Qualcomm Technologies Confidential and Proprietary
 *
 */
#include <stdio.h>
#include <log.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>

#define NUM_OF_THREAD 8
#define NUM_ITERATION 10000

void *printThread(void *threadid) {
long tid = (long)threadid;
for(long i=0; i<NUM_ITERATION; i++){
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
    ALOGI("Thread id: %ld, Iter No: %d\n",tid,i);
}
pthread_exit(NULL);
}

int main() {
    pthread_t threads[NUM_OF_THREAD];
    int rc;
    long t;

    for (t=0; t<NUM_OF_THREAD; t++) {
	ALOGI("Creating thread %ld\n", t);
        rc = pthread_create(&threads[t], NULL, printThread, (void *)t);
	if (rc){
		printf("ERROR %d\n",rc);
		exit(-1);
	}
    }
    for(t=0; t<NUM_OF_THREAD; t++){
	    pthread_join(threads[t],NULL);
    }
    pthread_exit(NULL);
    return 0;
}
