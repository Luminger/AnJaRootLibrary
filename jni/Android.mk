LOCAL_PATH := $(call my-dir)

ANJAROOTHOOK_LOGTAG := AnJaRootHook
ANJAROOTNATIVE_LOGTAG := AnJaRootNative
ANJAROOTINSTALLER_LOGTAG := AnJaRootInstaller


include $(CLEAR_VARS)
LOCAL_MODULE := anjaroot
LOCAL_SRC_FILES :=	libs/wrapper.cpp \
					libs/exceptions.cpp \
					util.cpp
LOCAL_LDLIBS := -llog -ldl
LOCAL_CPP_INCLUDES := $(LOCAL_PATH)
LOCAL_CPP_FEATURES := exceptions
LOCAL_CPPFLAGS := -DANJAROOT_LOGTAG="\"$(ANJAROOTNATIVE_LOGTAG)\"" \
				  -std=c++0x -Wall
include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE := anjaroothook
LOCAL_SRC_FILES := libs/hook.cpp \
				   libs/packages.cpp \
				   util.cpp
LOCAL_LDLIBS := -llog -ldl
LOCAL_CPP_INCLUDES := $(LOCAL_PATH)
LOCAL_CPP_FEATURES := exceptions
LOCAL_CPPFLAGS := -DANJAROOT_LOGTAG="\"$(ANJAROOTHOOK_LOGTAG)\"" \
				  -std=c++0x -Wall

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := anjarootinstaller
LOCAL_SRC_FILES := installer/installer.cpp \
				   installer/operations.cpp \
				   util.cpp
LOCAL_LDLIBS := -llog
LOCAL_CPP_INCLUDES := $(LOCAL_PATH)
LOCAL_CPP_FEATURES := exceptions
LOCAL_CPPFLAGS := -DANJAROOT_LOGTAG="\"$(ANJAROOTINSTALLER_LOGTAG)\"" \
				  -std=c++0x -Wall
include $(BUILD_EXECUTABLE)
