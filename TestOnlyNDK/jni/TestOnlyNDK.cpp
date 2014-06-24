#include <jni.h>
#include <android_native_app_glue.h>
#include <android/log.h>

#include <dlfcn.h>
#include <errno.h>
#include <stdlib.h>

#define TAG "TestAllNDK"

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__))

//#define LIB_PATH "/data/data/@PH_BUNDLE_ID@/lib/"


void showAds() {
	struct android_app *__gandroidapp__;

	// Get the android application's activity.
	ANativeActivity* activity = __gandroidapp__->activity;

	JavaVM* jvm = activity->vm;

	JNIEnv* env = NULL;

//	(*jvm)->GetEnv(jvm, (void**) &env, JNI_VERSION_1_6);
	jvm->GetEnv((void**) &env, JNI_VERSION_1_6);

	//jint res = (*jvm)->AttachCurrentThread(jvm, &env, NULL);
	jint res = jvm->AttachCurrentThread(&env, NULL);

	if (res == JNI_ERR)	{
		// Failed to retrieve JVM environment
		return;
	}

//	jclass clazz = (*env)->GetObjectClass(env, activity->clazz);
	jclass clazz = env->GetObjectClass(activity->clazz);

	//jmethodID methodID = (*env)->GetMethodID(env, clazz, "showAdPopup", "()V");
	jmethodID methodID = env->GetMethodID(clazz, "showAdPopup", "()V");

	//(*env)->CallVoidMethod(env, activity->clazz, methodID);
	env->CallVoidMethod(activity->clazz, methodID);

//	(*jvm)->DetachCurrentThread(jvm);
	jvm->DetachCurrentThread();
}

static int32_t OnInput(struct android_app* app, AInputEvent* event) {

	if (AKeyEvent_getKeyCode(event) == AKEYCODE_BACK) {
		// actions on back key
//		showAds();
		return 1; // <-- prevent default handler
	};

	__android_log_write(ANDROID_LOG_ERROR, "MyNativeProject", "Hello input event!");

	return 0;
}

extern "C" void android_main(struct android_app* App) {
	app_dummy();
	App->onInputEvent = OnInput;

	for (;;) {
		struct android_poll_source* source;
		int ident;
		int events;

		while ((ident = ALooper_pollAll(-1, NULL, &events, (void**) &source)) >= 0) {
			if (source != NULL)
				source->process(App, source);

			if (App->destroyRequested != 0)
				return;
		}
	}
}

//void * load_lib(const char * l) {
//	void * handle = dlopen(l, RTLD_NOW | RTLD_GLOBAL);
//	if (!handle) {
//		LOGE("dlopen(\"%s\"): %s", l, strerror(errno));
//		exit(1);
//	}
//	return handle;
//}

//void ANativeActivity_onCreate(ANativeActivity * app, void * ud, size_t udsize) {
//	LOGE("ANativeActivity_onCreate");
//    load_lib(LIB_PATH "libpng15.so");
//    load_lib(LIB_PATH "liblua.so");
//    load_lib(LIB_PATH "libopenal.so");
//    load_lib(LIB_PATH "libPorkholt.so");
//    void (*main)(ANativeActivity*, void*, size_t) = dlsym(load_lib(LIB_PATH "lib@PH_APP_TARGET@.so"), "ANativeActivity_onCreate");
//    if (!main)
//    {
//        LOGE("undefined symbol ANativeActivity_onCreate");
//        exit(1);
//    }
//    main(app, ud, udsize);
//}
