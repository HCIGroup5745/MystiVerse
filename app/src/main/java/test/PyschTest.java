/*
package test;

;
import android.os.Bundle;

import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier;


import com.example.luck.R;
import android.content.Intent;
import android.net.Uri;

import androidx.compose.ui.graphics.Color;

import androidx.compose.ui.text.font.FontFamily;


import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.runtime.Composable;
;import com.example.luck.databinding.ActivityLuckBinding;


public class PyschTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       face() = ActivityLuckBinding.inflate(getLayoutInflater());


    }
}







@Composable

public class SomeJavaClass {

    @Composable
    public static void face() {
        Column {
            TextKt.Text(
                    "心理测试",
                    30.sp,
                    new androidx.compose.ui.text.font.FontFamily(
                            new androidx.compose.ui.text.font.Font(R.font.opposans_b)
                    ),
                    Modifier.padding(30.d, 30.dp, 30.dp)
            );
            LazyColumn {
                items(TestsListKt.generateTestsList());
            }
        };
    }
}



public class TestsList {

    @Composable
    public static void TestsList(String title, String des, String url) {
        Box(
                Modifier.padding(20.dp, 15.dp, 20.dp)
        ) {
            Column(
                    Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.Companion.Companion.Unspecified)
                            .clickable(
                                    onClick = () -> {
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                        intent.setData(Uri.parse(url));
                                        LocalContext.current.startActivity(intent);
                                    },
                                    indication = rememberRipple(bounded = false, radius = 15.dp)
                            )
            ) {
                Text(
                        title,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Companion.Companion.Default,
                        modifier = Modifier.padding(15.dp, 15.dp, 15.dp)
                );
                Text(
                        des,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Companion.Companion.Default,
                        modifier = Modifier.padding(15.dp, 0.dp, 15.dp)
                );
            }
        };
    }
}
*/
