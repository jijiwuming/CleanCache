#import "CleanCache.h"  
#import <Cordova/CDV.h>  
  
@implementation CleanCache  
  
- (void)CleanCache:(CDVInvokedUrlCommand*)command  
    {  
        NSLog(@"OK");
        [[NSURLCache sharedURLCache] removeAllCachedResponses];
        NSArray *paths = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES);
        NSString *docDir = [paths objectAtIndex:0];
        NSArray *files = [[NSFileManager defaultManager] subpathsOfDirectoryAtPath:docDir error:nil];
        //遍历数组
        BOOL flag = NO;
        for (NSString *fileName in files) {
            NSString *filePath = [docDir stringByAppendingPathComponent:fileName];
            NSFileManager *mgr = [NSFileManager defaultManager];
            flag = [mgr removeItemAtPath:filePath error:nil];            
            if (!flag)
                break;
        }
        NSString *tmpDir = NSTemporaryDirectory();
        NSArray *tmpfiles = [[NSFileManager defaultManager] subpathsOfDirectoryAtPath:tmpDir error:nil];
        //遍历数组
        BOOL tmpflag = NO;
        for (NSString *fileName in tmpfiles) {
            NSString *filePath = [tmpDir stringByAppendingPathComponent:fileName];
            NSFileManager *mgr = [NSFileManager defaultManager];
            flag = [mgr removeItemAtPath:filePath error:nil];
            if (!tmpflag)
                break;
        }
        NSLog(@"cleaned");
        CDVPluginResult* pluginResult = nil;  
        //获取exec中传过来的参数  
        NSString* echo = [command.arguments objectAtIndex:0];  
  
        if (echo != nil && [echo length] > 0) {  
            //返回成功，messageAsString将数据返回到JavaScript。  
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];  
        } else {  
            //返回失败。  
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];  
        }  
        //将结果发送给<code>self.commandDelegate</code>，这样会执行JavaScript side的成功或失败方法。  
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];  
   } 
   @end