package cp2016.pagerank.parse;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class App {
    public static void main( String[] args ) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
    	Configuration config = new Configuration();
    	config.set("mapreduce.output.textoutputformat.separator", "\t");
    	
		Job job = Job.getInstance(config, "PageLinkBuilder");
		job.setJarByClass(App.class);

		job.setMapperClass(RowMapper.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}