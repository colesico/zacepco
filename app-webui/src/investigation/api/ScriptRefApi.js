import  * as restletUtils from "@common/assist/RestletUtils.js"; 

export async function lastScriptRefs(limit, offset){
	return await restletUtils.restletGet("/api/investigation/script-ref/last-script-refs",{limit,offset});
}