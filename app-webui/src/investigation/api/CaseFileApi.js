import  * as restletUtils from "@common/assist/RestletUtils.js"; 

export async function lastCases(limit, offset){
	return await restletUtils.restletGet("/api/investigation/criminal-case/last-cases",{limit,offset});
}