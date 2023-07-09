package com.example.myfoodapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodapp.R;
import com.example.myfoodapp.SQLite.DailyHelper;
import com.example.myfoodapp.adapters.HomeAdapter;
import com.example.myfoodapp.adapters.HomeVerAdapter;
import com.example.myfoodapp.adapters.UpdateVertical;
import com.example.myfoodapp.models.HomeModel;
import com.example.myfoodapp.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVertical {


    RecyclerView home_rec, home_ver_rec;
    ArrayList<HomeModel> homeModelList;

    HomeAdapter homeAdapter;

    /////////////////////////////////vertical//////////////////////////////
    ArrayList<HomeVerModel> homeVerModels;
    HomeVerAdapter homeVerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        home_rec = root.findViewById(R.id.home_item);
        home_ver_rec = root.findViewById(R.id.home_ver);
        //-----------------------------------------------------------------------------------------------------------------------------
        //--------------------------------SQLite
        DailyHelper helper = new DailyHelper(getContext(), "FoodApp.sqlite", null, 1);
        helper.QueryData("CREATE TABLE IF NOT EXISTS LoaiThucAn (ID_LoaiThucAn VARCHAR(4) Primary Key, Image_Loai INTEGER, Name NVARCHAR(200), DISCOUNT VARCHAR(100), DESCRIPTION NVARCHAR(200))");

        try {
//            helper.QueryData("DELETE FROM ThucAn");
            helper.QueryData("DELETE FROM LoaiThucAn");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L001', " + R.drawable.breakfast + ", 'Breakfast', '30% OFF', 'Được chuẩn bị cẩn thận và đầy đủ dinh dưỡng, bữa sáng là món ăn quan trọng để bắt đầu một ngày mới.')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L002', " + R.drawable.lunch + ", 'Lunch', '19% OFF', 'Thưởng thức bữa trưa ngon lành là cách hoàn hảo để nạp năng lượng cho phần còn lại của ngày')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L003', " + R.drawable.dinner + ", 'Dinner', '50% OFF', 'Bữa tối là thời gian để thưởng thức, tận hưởng và tạo ra những kỷ niệm đáng nhớ cùng những người thân yêu và bạn bè!')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L004', " + R.drawable.sweets + ", 'Sweets', '10% OFF', 'Khi đã thưởng thức bữa ăn chính ngon lành, không thể thiếu một chút ngọt ngào để hoàn thành bữa ăn')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L005', " + R.drawable.coffe + ", 'Coffe', '15% OFF', 'Thưởng thức hương vị cà phê thơm ngon là một cách tuyệt vời để bắt đầu hoặc kết thúc một ngày')");

            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L006', " + R.drawable.pizza3 + ", 'pizza', '23% OFF', 'Tận hưởng hương vị tuyệt vời và trải nghiệm các loại pizza đa dạng, từ pizza truyền thống đến những biến thể đặc biệt đầy sáng tạo')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L007', " + R.drawable.burger2 + ", 'hamburger', '26% OFF', 'Với chiếc bánh mì giòn tan, thịt bò mềm mại và các thành phần phụ như phô mai, rau sống, và sốt tùy chọn, hamburger là một sự kết hợp tuyệt vời giữa hương vị và độ ngon')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L008', " + R.drawable.fries1 + ", 'fries', '12% OFF', 'Thưởng thức khoai tây chiên và cảm nhận sự hài lòng khi nếm những miếng khoai tây vàng ươm cùng gia đình và bạn bè!')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L009', " + R.drawable.sandwich3 + ", 'sandwich', '25% OFF', 'Sandwich là một món ăn linh hoạt và tiện lợi, phổ biến trên khắp thế giới. Với việc kết hợp các thành phần như bánh mì mềm mịn, thịt, phô mai, rau sống và sốt tùy chọn')");
            helper.QueryData("INSERT INTO LoaiThucAn VALUES ('L010', " + R.drawable.icecream1 + ", 'Icecream', '5% OFF', 'Kem là một món tráng miệng ngọt ngào và thú vị, đặc biệt trong những ngày hè nóng bức')");
        } catch (Exception e) {
            Log.e("Lỗi", "Đã có dữ liệu trong bảng LoaiThucAn");
        }
        //--------------Thuc An Noi Bat
        helper.QueryData("CREATE TABLE IF NOT EXISTS ThucAnNoiBat (ID_Featured VARCHAR(10) PRIMARY KEY, Image_Featured INTEGER, Name_Featured NVARCHAR(200), Description_Featured NVARCHAR)");

        try {
            helper.QueryData("DELETE FROM ThucAnNoiBat");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F01', " + R.drawable.fav1 + ", 'Breakfast', 'Bữa sáng đầy đủ dinh dưỡng')");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F02', " + R.drawable.fav2 + ", 'Lunch', 'Bữa trưa tràn đầy năng lượng')");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F03', " + R.drawable.fav3 + ", 'Dinner', 'Bữa tối ấm áp cùng gia đình')");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F04', " + R.drawable.sweets + ", 'Sweet', 'Món tráng miệng sau những bữa ăn')");
            helper.QueryData("INSERT INTO ThucAnNoiBat VALUES ('F05', " + R.drawable.coffe + ", 'Cofee', 'Thưởng thức hương vị cà phê thơm ngon')");

        } catch (Exception e) {
            Log.e("err", "Đã có dữ liệu trong bảng ThucAnNoiBat");
        }

        //tao bang Giỏ Hàng

//        helper.QueryData("DROP TABLE ThucAn");
        helper.QueryData("CREATE TABLE IF NOT EXISTS ThucAn (ID_ThucAn VARCHAR(10) PRIMARY KEY,Image INTEGER, Name NVARCHAR(200), DESCRIPTION NVARCHAR(200), RATING VARCHAR(100), PRICE FLOAT, TIMING VARCHAR(100), ID_LoaiThucAn VARCHAR(4) REFERENCES LoaiThucAn(ID_LoaiThucAn), ID_Featured VARCHAR(10) REFERENCES ThucAnNoiBat(ID_Featured))");


        helper.QueryData("DELETE FROM ThucAn");
        helper.QueryData("INSERT INTO ThucAn VALUES ('BF01', " + R.drawable.fav1 + ", 'Ngũ cốc', 'Ngũ cốc dinh dưỡng', '4.4', 40, '6H đến 22H', 'L001', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('BF02', " + R.drawable.fav2 + ", 'Bánh hamburger phô mai', 'Bánh hamburger phô mai dinh dưỡng', '4.6', 35, '6H đến 22H', 'L001', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('BF03', " + R.drawable.fav3 + ", 'Mì trộn', 'Mì trộn full topping', '4.8', 20, '6H đến 22H', 'L001', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('L001', " + R.drawable.ver1 + ", 'Hạt dinh dưỡng', 'Best Seller', '4.2', 60, '6H đến 22H', 'L002', 'F02')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('L002', " + R.drawable.ver2 + ", 'Sandwich trứng', 'Best Seller', '4.4', 30, '6H đến 22H', 'L002', 'F02')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('L003', " + R.drawable.ver3 + ", 'Sandwich kiwi', 'Best Seller', '4.7', 50, '6H đến 22H', 'L002', 'F02')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('L004', " + R.drawable.ver1 + ", 'Hạt ngũ cốc', 'Best Seller', '4.1', 50, '6H đến 22H', 'L002', 'F02')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('D001', " + R.drawable.fries1 + ", 'Khoai tây chiên', 'Best Seller', '3.2', 30, '6H đến 22H', 'L003', 'F03')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('D002', " + R.drawable.fries2 + ", 'Combo khoai tây chiên x Thịt nướng', 'Best Seller', '4.5', 28, '6H đến 22H', 'L003', 'F03')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('D003', " + R.drawable.fries3 + ", 'Khoai tây chiên phô mai nướng', 'Best Seller', '4.4', 30, '6H đến 22H', 'L003', 'F03')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('D004', " + R.drawable.fries4 + ", 'Khoai tây chiên truyền thống', 'Best Seller', '4.8', 40, '6H đến 22H', 'L003', 'F03')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('S001', " + R.drawable.s1 + ", 'Kẹo ngọt', 'Best Seller', '3.3', 20, '6H đến 22H', 'L004', 'F04')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('S002', " + R.drawable.s2 + ", 'Bánh donut socola', 'Best Seller', '5', 35, '6H đến 22H', 'L004', 'F04')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('S003', " + R.drawable.s3 + ", 'Kem dâu', 'Best Seller', '5', 30, '6H đến 22H', 'L004', 'F04')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('S004', " + R.drawable.s4 + ", 'Bánh nướng x dây tây', 'Best Seller', '4.2', 50, '6H đến 22H', 'L004', 'F04')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('C001', " + R.drawable.cf1 + ", 'Cafee leaf', 'Best Seller', '3.4', 26, '6H đến 22H', 'L005', 'F05')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('C002', " + R.drawable.cf2 + ", 'Cafee đen', 'Best Seller', '4.5', 30, '6H đến 22H', 'L005', 'F05')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('C003', " + R.drawable.cf3 + ", 'Cafee kem', 'Best Seller', '4.2', 20, '6H đến 22H', 'L005', 'F05')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('C004', " + R.drawable.cf4 + ", 'Cafee trứng', 'Best Seller', '4.8', 55, '6H đến 22H', 'L005', 'F05')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('C005', " + R.drawable.cf5 + ", 'Cafee sữa', 'Best Seller', '4.6', 22, '6H đến 22H', 'L005', 'F05')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('PZ01', " + R.drawable.pizza1 + ", 'pizza phô mai', 'Best Seller', '4.8', 80, '6H đến 22H', 'L006', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('PZ02', " + R.drawable.pizza2 + ", 'pizza truyền thống', 'Best Seller', '4.4', 120, '6H đến 22H', 'L006', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('PZ03', " + R.drawable.pizza3 + ", 'pizza xúc xích', 'Best Seller', '4.1', 100, '6H đến 22H', 'L006', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('PZ04', " + R.drawable.pizza4 + ", 'pizza chay', 'Best Seller', '3.8', 60, '6H đến 22H', 'L006', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('HB01', " + R.drawable.burger1 + ", 'hamburger thịt xông khói', 'Best Seller', '4.6', 140, '6H đến 22H', 'L007', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('HB02', " + R.drawable.burger2 + ", 'hamburger phô mai thịt mini', 'Best Seller', '4.9', 130, '6H đến 22H', 'L007', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('HB03', " + R.drawable.burger4 + ", 'hamburger 2 tầng', 'Best Seller', '4.1', 150, '6H đến 22H', 'L007', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('FR01', " + R.drawable.fries1 + ", 'Khoai tây chiên', 'Best Seller', '4.3', 20, '6H đến 22H', 'L008', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('FR02', " + R.drawable.fries2 + ", 'Khoai tây rong biển', 'Best Seller', '4.2', 25, '6H đến 22H', 'L008', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('FR03', " + R.drawable.fries3 + ", 'Khoai tây phô mai', 'Best Seller', '5', 30, '6H đến 22H', 'L008', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('FR04', " + R.drawable.fries4 + ", 'Khoai tây truyền thống', 'Best Seller', '4.2', 20, '6H đến 22H', 'L008', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('SW01', " + R.drawable.sandwich1 + ", 'sandwich nướng', 'Best Seller', '4.9', 25, '6H đến 22H', 'L009', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('SW02', " + R.drawable.sandwich2 + ", 'sandwich thịt nguội', 'Best Seller', '5', 20, '6H đến 22H', 'L009', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('SW03', " + R.drawable.sandwich3 + ", 'sandwich truyền thống', 'Best Seller', '4.5', 25, '6H đến 22H', 'L009', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('SW04', " + R.drawable.sandwich4 + ", 'sandwich x khoai tây', 'Best Seller', '4', 20, '6H đến 22H', 'L009', 'F01')");

        helper.QueryData("INSERT INTO ThucAn VALUES ('IC01', " + R.drawable.icecream1 + ", 'Kem socola', 'Best Seller', '5', 25, '6H đến 22H', 'L010', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('IC02', " + R.drawable.icecream2 + ", 'Kem dừa', 'Best Seller', '3.8', 20, '6H đến 22H', 'L010', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('IC03', " + R.drawable.icecream3 + ", 'Kem dừa socola', 'Best Seller', '4.3', 35, '6H đến 22H', 'L010', 'F01')");
        helper.QueryData("INSERT INTO ThucAn VALUES ('IC04', " + R.drawable.icecream4 + ", 'kem dâu', 'Best Seller', '5', 30, '6H đến 22H', 'L010', 'F01')");

//        helper.QueryData("DROP TABLE GioHang");
        helper.QueryData("CREATE TABLE IF NOT EXISTS GioHang (ID_GioHang INTEGER PRIMARY KEY AUTOINCREMENT , ID_ThucAn VARCHAR(10) REFERENCES ThucAn(ID_ThucAn), SoLuong INTEGER, Email VARCHAR(200))");

        //bang lich su dat hang
//        helper.QueryData("DROP TABLE ChiTietDonHang");
//        helper.QueryData("DELETE FROM ChiTietDonHang");
        helper.QueryData("CREATE TABLE IF NOT EXISTS ChiTietDonHang (ID_DonHang INTEGER PRIMARY KEY AUTOINCREMENT, TenThucAn NVARCHAR(200), HoTen NVARCHAR(200), DiaChi NVARCHAR(200), TongTien FLOAT, NgayDatHang VARCHAR(200), PhuongThuc NVARCHAR(200), Email VARCHAR(200), TrangThai INTEGER)");

        homeModelList = new ArrayList<>();
        homeModelList.add(new HomeModel(R.drawable.pizza, "Pizza"));
        homeModelList.add(new HomeModel(R.drawable.hamburger, "Hamburger"));
        homeModelList.add(new HomeModel(R.drawable.fried_potatoes, "Fries"));
        homeModelList.add(new HomeModel(R.drawable.ice_cream, "Ice Cream"));
        homeModelList.add(new HomeModel(R.drawable.sandwich, "Sandwich"));
        homeAdapter = new HomeAdapter(this, getActivity(), homeModelList, getContext());
        home_rec.setAdapter(homeAdapter);
        home_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        home_rec.setHasFixedSize(true);
        home_rec.setNestedScrollingEnabled(false);


        /////////////////////////////////vertical//////////////////////////////
        homeVerModels = new ArrayList<>();

        homeVerAdapter = new HomeVerAdapter(getActivity(), homeVerModels);
        home_ver_rec.setAdapter(homeVerAdapter);
        home_ver_rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        return root;
    }

    @Override
    public void callback(int pos, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(), list);
        homeVerAdapter.notifyDataSetChanged();
        home_ver_rec.setAdapter(homeVerAdapter);

    }
}