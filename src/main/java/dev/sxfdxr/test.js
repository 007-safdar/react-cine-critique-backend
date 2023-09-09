var _wfx_user_name;
var _wfx_company_name;
var _wfx_library_name;
var _wfx_last_login_date;
var _wfx_is_admin;
var _wfx_is_ever_logged;
var _wfx_user_groups;
var _wfx_pii_properties = {};



function _wfx_set_userProperties() {
    console.log(window.config.clientInfo.whatfix);
    _wfx_properties = {
        CompanyName: window.config.clientInfo.whatfix.company_name != undefined ? window.config.clientInfo.whatfix.company_name : '',
        UserName: window.config.clientInfo.whatfix.user_name != undefined ? window.config.clientInfo.whatfix.user_name : '',
        LibraryName: window.config.clientInfo.whatfix.library_name != undefined ? window.config.clientInfo.whatfix.library_name : '',
        LastLoginDate: window.config.clientInfo.whatfix.last_login_date != undefined ? window.config.clientInfo.whatfix.last_login_date : '',
        IsAdmin: window.config.clientInfo.is_admin != undefined ? window.config.clientInfo.is_admin : '',
        IsEverLogged: window.config.clientInfo.whatfix.is_ever_logged != undefined ? window.config.clientInfo.whatfix.is_ever_logged : '',
        UserGroups: window.config.clientInfo.whatfix.user_groups != undefined ? JSON.stringify(window.config.clientInfo.whatfix.user_groups).replace(/[\[\]']+/g, '') : ''
    };
    _wfx_analytics_user_properties(_wfx_properties, _wfx_pii_properties);
}

var _wfx_countCheck = 0;
var _wfx_setUserData = setInterval(function () {
    _wfx_countCheck++;
    if (window.config && window.config.clientInfo && window.config.clientInfo.whatfix) {
        _wfx_settings.user = window.config.clientInfo.current_user_id;
        clearInterval(_wfx_setUserData);
        _wfx_set_userProperties();

    }
    else if (_wfx_countCheck > 30) {
        clearInterval(_wfx_setUserData);
    }
}, 250);