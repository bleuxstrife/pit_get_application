#import "PitGetApplicationPlugin.h"
#import <pit_get_application/pit_get_application-Swift.h>

@implementation PitGetApplicationPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftPitGetApplicationPlugin registerWithRegistrar:registrar];
}
@end
