class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/bluevia/inbox" (controller: "bluevia", action:"inboundSMSs")
        "/evernote/inbox" (controller: "evernote",action:"listNotebooks")

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
