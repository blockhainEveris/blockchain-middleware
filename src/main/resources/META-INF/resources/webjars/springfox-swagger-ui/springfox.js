 $(function() {
            var springfox = {
                "baseUrl": function() {
                	var urlMatches = /(.*)\/.*/.exec(window.location.href);
                	console.log('urlMatches: ', urlMatches);
                    return urlMatches[1];
                },
                "securityConfig": function(cb) {
                    $.getJSON(this.baseUrl() + "/swagger-resources/configuration/security", function(data) {
                        cb(data);
                    });
                },
                "uiConfig": function(cb) {
                    $.getJSON(this.baseUrl() + "/swagger-resources/configuration/ui", function(data) {
                        cb(data);
                    });
                }
            };
            window.springfox = springfox;
            window.oAuthRedirectUrl = springfox.baseUrl() + '/webjars/springfox-swagger-ui/o2c.html';

            window.springfox.uiConfig(function(data) {
                window.swaggerUi = new SwaggerUi({
                    dom_id: "swagger-ui-container",
                    validatorUrl: data.validatorUrl,
                    supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
                    onComplete: function(swaggerApi, swaggerUi) {
                        initializeSpringfox();
                        if (window.SwaggerTranslator) {
                            window.SwaggerTranslator.translate();
                        }
                        $('pre code').each(function(i, e) {
                            hljs.highlightBlock(e)
                        });
                    },
                    onFailure: function(data) {
                        log("Unable to Load SwaggerUI");
                    },
                    docExpansion: "none",
                    apisSorter: "alpha",
                    showRequestHeaders: false
                });

                initializeBaseUrl();

                $('#select_baseUrl').change(function() {
                    window.swaggerUi.headerView.trigger('update-swagger-ui', {
                        url: $('#select_baseUrl').val()
                    });
                    addApiKeyAuthorization();
                });

                function addApiKeyAuthorization() {
                    var authToken = sessionStorage.getItem("token");
                    var apiKeyAuth = new SwaggerClient.ApiKeyAuthorization("Authorization",
                            "Bearer " + authToken, "header");
                    window.swaggerUi.api.clientAuthorizations.add("key", apiKeyAuth);
                }

                function getCSRF() {
                    var name = "XSRF-TOKEN=";
                    var ca = document.cookie.split(';');
                    for(var i=0; i<ca.length; i++) {
                        var c = ca[i];
                        while (c.charAt(0)==' ') c = c.substring(1);
                        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
                    }
                    return "";
                }

                function log() {
                    if ('console' in window) {
                        console.log.apply(console, arguments);
                    }
                }

                function oAuthIsDefined(security) {
                    return security.clientId
                    && security.clientSecret
                    && security.appName
                    && security.realm;
                }

                function initializeSpringfox() {
                    var security = {};
                    window.springfox.securityConfig(function(data) {
                        security = data;
                        if (typeof initOAuth == "function" && oAuthIsDefined(security)) {
                            initOAuth(security);
                        }
                    });
                }
            });

            function maybePrefix(location, withRelativePath) {
                var pat = /^https?:\/\//i;
                if (pat.test(location)) {
                    return location;
                }
                return withRelativePath + location;
            }

            function initializeBaseUrl() {
                var relativeLocation = springfox.baseUrl();

                $('#input_baseUrl').hide();

                $.getJSON(relativeLocation + "/swagger-resources", function(data) {

                    var $urlDropdown = $('#select_baseUrl');
                    $urlDropdown.empty();
                    $.each(data, function(i, resource) {
                        var option = $('<option></option>')
                        .attr("value", maybePrefix(resource.location, relativeLocation))
                        .text(resource.name + " (" + resource.location + ")");
                        $urlDropdown.append(option);
                    });
                    $urlDropdown.change();
                });

            }

        });