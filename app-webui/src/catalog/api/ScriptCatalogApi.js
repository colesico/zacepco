import  * as restletUtils from "@common/assist/RestletUtils.js"; 

export async function lastScripts(limit, offset){
	return await restletUtils.restletGet("/api/scripts/script-ref/last-script-refs",{limit,offset});
}