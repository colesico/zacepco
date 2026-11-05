import  * as restletUtils from "@core/assist/RestletUtils.js"; 

export async function createInvestigationEntry(request){
	return await restletUtils.restletPost("/docket/api/investigation",request,{});
}

