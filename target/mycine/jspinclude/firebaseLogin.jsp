<script src="https://www.gstatic.com/firebasejs/3.6.9/firebase.js"></script>
<script>
    // Initialize Firebase
    var config = {
        apiKey: "AIzaSyDLrdvljKPzrrSTo3t3jzoDcc952cxrBPg",
        authDomain: "mycine-7f481.firebaseapp.com",
        databaseURL: "https://mycine-7f481.firebaseio.com",
        storageBucket: "mycine-7f481.appspot.com",
        messagingSenderId: "1057722349919"
    };
    firebase.initializeApp(config);
</script>
<script src="https://cdn.firebase.com/libs/firebaseui/1.0.0/firebaseui.js"></script>
<link type="text/css" rel="stylesheet" href="https://cdn.firebase.com/libs/firebaseui/1.0.0/firebaseui.css" />

<script type="text/javascript">
    var uiConfig = {
        callbacks: {
            signInSuccess: function (currentUser, credential, redirectURL) {
                return true;
            },

            uiShown: function () {
                document.getElementById('loader').style.display = 'none';
            }
         },

        credentialHelper: firebaseui.auth.CredentialHelper.ACCOUNT_CHOOSER_COM,

        queryParameterForWidgetMode: 'mode',

        queryParameterForSignInSuccessUrl: 'signInSuccessUrl',

        signInFlow: 'popup',

        //signInSuccessURL: 'RegCheck?fbUUID=' + firebase.User,
        signInSuccessURL: 'RegCheck?fbUUID=B76OtOQ3TfaFQfGu19ar57j85Uc2',
        signInOptions : [
            firebase.auth.GoogleAuthProvider.PROVIDER_ID,
            firebase.auth.FacebookAuthProvider.PROVIDER_ID,
            //firebase.auth.TwitterAuthProvider.PROVIDER_ID,
            //firebase.auth.GithubAuthProvider.PROVIDER_ID,
            firebase.auth.EmailAuthProvider.PROVIDER_ID
        ],

        tosUrl: 'http://www.google.com'
    };

    var ui = new firebaseui.auth.AuthUI(firebase.auth());

    ui.start('#firebaseui-auth-container', uiConfig);
</script>

