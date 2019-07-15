package se.dsve.top10downloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
* something
*/

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> applications;

    public FeedAdapter(Context context, int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        TextView tvName = convertView.findViewById(R.id.tvName);
//        TextView tvArtist = convertView.findViewById(R.id.tvArtist);
//        TextView tvSummary = convertView.findViewById(R.id.tvSummary);
//        ImageView tvImage = view.findViewById(R.id.tvImage);

        FeedEntry currentApp = applications.get(position);

        viewHolder.tvName.setText(currentApp.getName());
        viewHolder.tvArtist.setText(currentApp.getArtist());
        viewHolder.tvSummary.setText(currentApp.getSummary());
        //tvImage.setImageDrawable(currentApp.getImageURL());

        return convertView;
    }

    private class ViewHolder {
        final TextView tvName;
        final TextView tvArtist;
        final TextView tvSummary;

        ViewHolder(View view) {
            this.tvName = view.findViewById(R.id.tvName);
            this.tvArtist = view.findViewById(R.id.tvArtist);
            this.tvSummary = view.findViewById(R.id.tvSummary);
        }

    }
}


/*
  __
 /\ \
 \_\ \    ____  __  __     __
 /'_` \  /',__\/\ \/\ \  /'__`\
/\ \L\ \/\__, `\ \ \_/ |/\  __/
\ \___,_\/\____/\ \___/ \ \____\
 \/__,_ /\/___/  \/__/   \/____/
       https://dsve.se/
*/

/**
 * @author Lars Strömberg
 * @version 1.0
 * @since 2019-07-06
 * https://github.com/deskavaenkelt/
 */